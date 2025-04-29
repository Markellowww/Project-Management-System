<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Система управления проектами</title>
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/auth.css">
</head>
<body>
    <div class="reg-container">
        <h1>
            Регистрация
        </h1>

        <form action="/reg" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="form-group">
                <label for="email">Почта</label>
                <input type="email" id="email" name="username" placeholder="E-mail" required>
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" placeholder="Пароль" required>
            </div>

            <input type="submit" value="Зарегистроваться">
        </form>

        <a href="/login" class="login-link">Войти</a>

        <#if error??>
            <div class="error-message">${error}</div>
        </#if>
    </div>
</body>
</html>