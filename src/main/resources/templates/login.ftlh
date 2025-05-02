<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Система управления проектами</title>
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/auth.css">
</head>
<body>
    <div class="login-container">
        <h1>
            Вход
        </h1>

        <form action="/login" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            <div class="form-group">
                <label for="email">Почта</label>
                <input type="email" id="email" name="username" placeholder="Введите ваш email" required>
            </div>

            <div class="form-group">
                <label for="password">Пароль</label>
                <input type="password" id="password" name="password" placeholder="Введите ваш пароль" required>
            </div>

            <input type="submit" value="Войти">
        </form>

        <a href="/reg" class="register-link">Зарегистрироваться</a>

        <#if error??>
            <div class="error-message">
                ${error}
            </div>
        </#if>
    </div>
</body>
</html>