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
		<div class="hello-msg">${userEmail}</div>
		<div class="dead-line">{Deadline}</div>
		<div class="profile">
			<a href="${springMacroRequestContext.contextPath}/profile" class="profile-link">
				Профиль
			</a>
		</div>
	</header>
	<div class="content">
		<div class="projects-container">
			<div class="project-add">
				<a href="${springMacroRequestContext.contextPath}/project/new" class="project-add-btn">
					+
				</a>
			</div>
			<#list projects as project>
				<div class="project">
					<div class="project-title">
						${project.title}
					</div>
					<div class="project-description">
						${project.description}
					</div>
					<a href="${springMacroRequestContext.contextPath}/projects/${project.id}" class="project-button">
						Перейти к проекту
					</a>
				</div>
			</#list>
		</div>
	</div>
</body>
</html>