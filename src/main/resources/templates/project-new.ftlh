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
            Выбрана команда «${team.name}»
        </div>
        <div class="choose-module">
            <h3>
                Создать проект
            </h3>
        </div>
        <div class="profile">
            <a href="${springMacroRequestContext.contextPath}/team/${team.getId()}" class="header-link">
                Назад
            </a>
        </div>
    </header>
    <div class="content">
        <div class="module-info">
            <form action="${springMacroRequestContext.contextPath}/team/${teamId}/project/new" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label for="title">Название проекта:</label>
                    <input type="text" id="title" name="title"
                           placeholder="Введите название проекта" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="description">Описание проекта:</label>
                    <textarea id="description" name="description"
                              placeholder="Опишите ваш проект"
                              rows="5" cols="50"></textarea>
                </div>
                <br>
                <div class="form-group">
                    <label for="endDate">Планируемая дата завершения:</label>
                    <input type="datetime-local" id="endDate" name="endDate">
                </div>
                <button type="submit" class="save-btn">Создать проект</button>
            </form>
        </div>
    </div>
</body>
</html>