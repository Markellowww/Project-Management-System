<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Система управления проектами</title>
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/style.css">
    <script src="${springMacroRequestContext.contextPath}/js/team-info.js" defer></script>
</head>
<body>
    <header class="header">
        <div class="left-msg">
            Выбрана команда «${team.getName()}»!
        </div>
        <div class="choose-module">
            <h3>
                Выберите проект
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
            <div class="form-group">
                <h3>
                    Команда: «${team.getName()}»
                </h3>
                <h3>
                Владелец: ${team.getOwner().getFirstname()} ${team.getOwner().getLastname()}
                </h3>
                <h3>
                    Описание: «${team.getDescription()}»
                </h3>
                <h3>
                    Участники: <span id="membersCount">${team.getMembers()?size}</span>
                    <button class="members-btn" onclick="openModal()">Подробнее</button>
                </h3>
            </div>
        </div>
        <div class="module-container">
            <div class="module-add">
                <a href="${springMacroRequestContext.contextPath}/team/${team.id}/project/new" class="module-add-btn">
                    +
                </a>
            </div>
<#--                    <#list projects as project>-->
<#--                        <div class="module">-->
<#--                            <div class="module-title">-->
<#--                                ${team.name}-->
<#--                            </div>-->
<#--                            <div class="module-description">-->
<#--                                ${team.description}-->
<#--                            </div>-->
<#--                            <a href="${springMacroRequestContext.contextPath}/team/${team.id}" class="module-button">-->
<#--                                Перейти к команде-->
<#--                            </a>-->
<#--                        </div>-->
<#--                    </#list>-->
        </div>
    </div>
    <div id="membersModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2 class="modal-title">Участники команды «${team.getName()}»</h2>
            <div class="members-list">
                <#list team.getMembers() as member>
                    <div class="member-item">${member?index + 1}. ${member.firstname} ${member.lastname}</div>
                </#list>
            </div>
        </div>
    </div>
</body>
</html>