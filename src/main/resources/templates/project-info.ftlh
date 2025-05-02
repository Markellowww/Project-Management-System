<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Система управления проектами</title>
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/style.css">
</head>
<body>
    <header class="header">
        <div class="dead-line">{Deadline}</div>
        <div class="profile">{Профиль}</div>
    </header>
    <br>
    <br>
    <br>
    <div>
        <div class="project-title">
            ${project.title}
        </div>
        <div class="project-description">
            ${project.description}
        </div>
        <div class="project-date">
            ${project.date}
        </div>
    </div>
</body>
</html>