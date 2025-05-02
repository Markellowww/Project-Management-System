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
    <div>
        <form action="/team/new" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <br><br><br>
            Название: <input type="text" name="name"/><br>
            Описание: <input type="text" name="description"/><br>
            <input type="submit" value="Создать команду"/>
        </form>
    </div>
</body>
</html>