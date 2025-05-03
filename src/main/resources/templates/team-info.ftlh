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
            Выбрана команда «${team.getName()}»
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
                    Команда: «${team.name}»
                </h3>
                <h3>
                    Владелец: ${team.owner.firstname} ${team.owner.lastname}
                </h3>
                <h3>
                    Описание: «${team.description}»
                </h3>
                <h3>
                    Участники: <span id="membersCount">${team.getMembers()?size}</span>
                    <button class="members-btn" onclick="openModal()">Подробнее</button>
                </h3>
                <#if isMember>
                    <div class="form-group">
                        <#if isOwner>
                            <form action="${springMacroRequestContext.contextPath}/team/${team.id}/delete" method="post"
                                  onsubmit="return confirm('Вы уверены, что хотите удалить команду? Это действие нельзя отменить.');">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="delete-btn">Удалить команду</button>
                            </form>
                        <#else>
                            <form action="${springMacroRequestContext.contextPath}/team/${team.id}/leave" method="post"
                                  onsubmit="return confirm('Вы уверены, что хотите удалить команду? Это действие нельзя отменить.');">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="delete-btn">Покинуть команду</button>
                            </form>
                        </#if>
                    </div>
                <#else>
                <div class="form-group">
                    <form action="${springMacroRequestContext.contextPath}/team/${team.id}/join" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="save-btn">Вступить в команду</button>
                    </form>
                </div>
                </#if>
            </div>
        </div>
        <#if isMember>
            <div class="module-container">
                <div class="module-add">
                    <a href="${springMacroRequestContext.contextPath}/team/${team.id}/project/new" class="module-add-btn">
                        +
                    </a>
                </div>
                <#list team.projects as project>
                    <div class="module">
                        <div class="module-title">
                            ${project.title}
                        </div>
                        <div class="module-description">
                            ${project.description}
                        </div>
                        <a href="${springMacroRequestContext.contextPath}/team/${team.id}/project/${project.id}" class="module-button">
                            Перейти к проекту
                        </a>
                    </div>
                </#list>
            </div>
        </#if>
    </div>
    <div id="membersModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeModal()">&times;</span>
            <h2 class="modal-title">
                Участники команды «${team.name}»
            </h2>
            <div class="members-list">
                <#list team.members as member>
                    <div class="member-item">
                        ${member?index + 1}. ${member.firstname} ${member.lastname}
                        <#if isOwner && member.id != user.id>
                            <form action="/team/${team.id}/kick/${member.id}"
                                  method="post"
                                  style="display: inline;">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <button type="submit" class="remove-member-btn"
                                        onclick="return confirm('Выгнать ${member.firstname} ${member.lastname}?')">
                                    ×
                                </button>
                            </form>
                        </#if>
                    </div>
                </#list>
            </div>
        </div>
    </div>
</body>
</html>