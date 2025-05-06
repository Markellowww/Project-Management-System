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
        Выбрана команда «${project.team.name}», проект «${project.title}»
    </div>
    <div class="profile">
        <a href="${springMacroRequestContext.contextPath}/team/${project.team.id}/project/${project.id}" class="header-link">
            Назад
        </a>
    </div>
</header>
    <div class="content">
        <div class="module-info">
            <form action="${springMacroRequestContext.contextPath}/team/${project.team.id}/project/${project.id}/new" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="form-group">
                    <label for="title">Название задания:</label>
                    <input type="text" id="title" name="title"
                           placeholder="Введите название задачи" required>
                </div>
                <br>
                <div class="form-group">
                    <label for="description">Описание задания:</label>
                    <textarea id="description" name="description"
                              placeholder="Опишите задачу"
                              rows="5" cols="50"></textarea>
                </div>
                <button type="submit" class="save-btn">Создать задачу</button>
            </form>
        </div>
    </div>
</body>
</html>