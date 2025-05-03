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
    <div class="module-info">
        <div class="form-group">
            <h3>
                Команда: «${teamName}»
            </h3>
            <h3>
            Владелец: ${teamOwner}
            </h3>
            <h3>
                Описание: «${teamDescription}»
            </h3>
            <h3>
                Участники:
<#--                <#list teamMembers as member>-->
<#--                    <div>${member?index + 1}. ${member.firstname} ${member.lastname}</div>-->
<#--                </#list>-->
                IN DEVELOP
            </h3>
        </div>
    </div>
</body>
</html>