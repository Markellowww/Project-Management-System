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
        <div class="logout-link">
            <form action="${springMacroRequestContext.contextPath}/logout" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="logout-btn">Выйти</button>
            </form>
        </div>
        <div class="choose-team">
            <h3>
                Настройки профиля
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
            <form action="${springMacroRequestContext.contextPath}/profile" method="post" class="module-form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${email}" required>
                </div>

                <div class="form-group">
                    <label for="firstname">Имя:</label>
                    <input type="text" id="firstname" name="firstname" value="${firstname}" required>
                </div>

                <div class="form-group">
                    <label for="lastname">Фамилия:</label>
                    <input type="text" id="lastname" name="lastname" value="${lastname}" required>
                </div>

                <div class="form-group">
                    <label for="password">Новый пароль (оставьте пустым, если не хотите менять):</label>
                    <input type="password" id="newPassword" name="newPassword">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">Подтвердите новый пароль:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword">
                </div>

                <div class="form-group">
                    <label for="currentPassword">Текущий пароль (для подтверждения изменений):</label>
                    <input type="password" id="currentPassword" name="currentPassword" required>
                </div>

                <button type="submit" class="save-btn">Сохранить изменения</button>
            </form>
            <#if error??>
                <div class="error-message">
                    ${error}
                </div>
            </#if>
            <#if success??>
                <div class="success-message">
                    ${success}
                </div>
            </#if>
        </div>
    </div>
</body>
</html>