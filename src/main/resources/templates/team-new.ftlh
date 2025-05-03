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
            Привет, ${firstname}!
        </div>
        <div class="choose-module">
            <h3>
                Создать команду
            </h3>
        </div>
        <div class="profile">
            <a href="${springMacroRequestContext.contextPath}/" class="header-link">
                Назад
            </a>
        </div>
    </header>
    <div class="content">
        <div class="module-info">
            <form action="/team/new" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label for="name">Название команды:</label>
                    <input type="text" id="name" name="name" placeholder="Введите название" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="description">Описание:</label>
                    <textarea id="description" name="description" placeholder="Введите описание" required
                              rows="5" cols="50"></textarea>
                </div>
                <button type="submit" class="save-btn">Создать команду</button>
            </form>
        </div>
    </div>
</body>
</html>