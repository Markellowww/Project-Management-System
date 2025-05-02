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
        <div class="left-msg">
            Выбрана команда «${teamName}»!
        </div>
        <div class="profile">
            <a href="${springMacroRequestContext.contextPath}/" class="header-link">
                Назад
            </a>
        </div>
    </header>
    <br>
    <br>
    <br>
    <div>
        <div class="module-title">
            ${team.name}
        </div>
        <div class="module-description">
            ${team.description}
        </div>
        <div class="module-date">
            ${team.id}
        </div>
    </div>
</body>
</html>