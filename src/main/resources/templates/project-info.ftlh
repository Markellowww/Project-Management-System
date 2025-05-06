<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Система управления проектами</title>
    <link rel="stylesheet" href="${springMacroRequestContext.contextPath}/css/style.css">
    <script src="${springMacroRequestContext.contextPath}/js/project-info.js" defer></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
    <header class="header">
        <div class="left-msg">
            Выбрана команда «${project.team.name}», проект «${project.title}»
        </div>
        <div class="choose-module">
            <h3>
                Осталось ${timeLeft.toDays()} дней ${timeLeft.toHoursPart()} часов ${timeLeft.toMinutesPart()} минут
            </h3>
        </div>
        <div class="profile">
            <a href="${springMacroRequestContext.contextPath}/team/${project.team.id}" class="header-link">
                Назад
            </a>
        </div>
    </header>
    <div class="content">
        <div class="module-info">
            <div class="form-group">
                <h3>
                    Проект: «${project.title}»
                </h3>
                <h3>
                    Создал: ${project.owner.firstname} ${project.owner.lastname}
                </h3>
                <h3>
                    Начало работ: ${startTime}
                </h3>
                <h3>
                    Планируемая дата завершения: ${endTime}
                </h3>
                <h3>
                    Описание: «${project.description}»
                </h3>
                <br>
                <h3>
                    Статистика по заданиям:
                </h3>
                <h3>
                    Общее кол-во: ${taskAmount}
                </h3>
                <#if taskAmount gt 0>
                    <#if taskStatus[1] gt 0>
                        <h3>В разработке: ${taskStatus[1]}</h3>
                    </#if>
                    <#if taskStatus[0] gt 0>
                        <h3>В рассмотрении: ${taskStatus[0]}</h3>
                    </#if>
                    <#if taskStatus[2] gt 0>
                        <h3>Замороженные: ${taskStatus[2]}</h3>
                    </#if>
                    <#if taskStatus[3] gt 0>
                        <h3>Отклоненные: ${taskStatus[3]}</h3>
                    </#if>
                    <#if taskStatus[4] gt 0>
                        <h3>Выполненные: ${taskStatus[4]}</h3>
                    </#if>
                </#if>
                <#if isOwner>
                    <form action="${springMacroRequestContext.contextPath}/team/${project.team.id}/project/${project.id}/delete" method="post"
                          onsubmit="return confirm('Вы уверены, что хотите удалить проект? Это действие нельзя отменить.');">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <button type="submit" class="delete-btn">Удалить проект</button>
                    </form>
                </#if>
            </div>
        </div>
        <div class="module-container">
            <div class="module-add">
                <a href="${springMacroRequestContext.contextPath}/team/${project.team.id}/project/${project.id}/task/new" class="module-add-btn">
                    +
                </a>
            </div>
            <#list tasks as task>
                <div class="module">
                    <div class="module-title">
                        ${task.title}
                    </div>
                    <div class="module-description">
                        ${task.description}
                    </div>
                    <div class="module-description">
                        Открыл: ${task.reporter.firstname} ${task.reporter.lastname}
                    </div>
                    <div class="module-description">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" id="csrfToken"/>
                        Статус:
                        <select class="status-select" data-task-id="${task.id}" data-project-id="${project.id}">
                            <#list taskStatuses as status>
                                <option value="${status.id}" <#if task.status?? && task.status.id == status.id>selected</#if>>
                                    ${status.name}
                                </option>
                            </#list>
                        </select>
                    </div>
                    <div class="module-description">
                        <button class="save-status-btn" data-task-id="${task.id}">Сохранить</button>
                        <#if isOwner>
                            <button class="delete-status-btn" data-task-id="${task.id}">Удалить</button>
                        </#if>
                    </div>
                </div>
            </#list>
        </div>
    </div>
</body>
</html>