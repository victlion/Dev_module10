<!DOCTYPE html>
<html>
<head>
    <title>Servlet</title>
</head>
<body>
<div style="text-align: center;">
    <h2>Start page Servlet</h2>
    <p>Test button.</p>
    <br>
    <br>
    <input type="button" onclick="location.href='/Dev_module10/time';" value="/TIME"/>
    <br>
    <br>
    <input type="button" onclick="location.href='/Dev_module10/time?timezone=UTC+2';" value="/UTC+2"/>
    <br>
    <br>
    <input type="button" onclick="location.href='/Dev_module10/time?timezone=UTC+20';" value="/UTC+20(code 400)"/>
</div>
</body>
</html>