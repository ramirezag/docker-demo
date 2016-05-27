<?php
    $apiUrl = getenv('STUDENT_API_URL');
    if(empty($apiUrl)) {
        $apiUrl = 'http://localhost:8080/api';
    }
    if(isset($_POST['add_grade'])) {
        $name = $_POST['name'];
        $grade = $_POST['grade'];
        $data = array("name" => $name, "grade" => $grade);
        $data_string = json_encode($data);
        $ch = curl_init("$apiUrl/student/grade/add");
        curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
        curl_setopt($ch, CURLOPT_POSTFIELDS, $data_string);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, array(
                'Content-Type: application/json',
                'Content-Length: ' . strlen($data_string))
        );
        curl_exec($ch);
        curl_close($ch);
    }
    $ch = curl_init("$apiUrl/student/grade/list");
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "GET");
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);

    $result = curl_exec($ch);
    if(curl_error($ch)) {
        $curl_error_message = curl_error($ch);
    }
    curl_close($ch);
    $array = json_decode($result);
?>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Student Webapp</title>
</head>
<body>
<form name="form" method="POST">
    <label>Student Name</label>:<input name="name" placeholder="Name" required />
    <label>Student Grade</label>:<input name="grade" placeholder="Grade" type="number" required/>
    <button name="add_grade" type="submit">Add</button>
</form>
<br />
<?php
    if(!empty($curl_error_message)) {
        echo "<div style='color: red'>CURL Error: $curl_error_message</div>";
    }
?>
<br />
<table>
    <thead>
        <tr>
            <th>Name</th>
            <th>Grades</th>
            <th>Average</th>
        </tr>
    </thead>
    <tbody>
    <?php
        if(!empty($array)) {
            foreach ($array as $student) {
                echo "<tr><td>$student->name</td><td>".implode(", ", $student->grades)."</td><td>$student->average</td></tr>";
            }
        } else {
            echo '<tr><td colspan=3>List is empty</td></tr>';
        }
    ?>
    </tbody>
</table>
</body>

</html>