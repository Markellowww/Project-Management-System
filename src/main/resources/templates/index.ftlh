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
		<div class="hello-msg">
			Привет, ${firstname}!
		</div>
		<div class="choose-team">
			<h3>
				Выберите команду
			</h3>
		</div>
		<div class="profile">
			<a href="${springMacroRequestContext.contextPath}/profile" class="header-link">
				Профиль
			</a>
		</div>
	</header>
	<div class="content">
		<div class="module-container">
			<div class="module-add">
				<a href="${springMacroRequestContext.contextPath}/project/new" class="module-add-btn">
					+
				</a>
			</div>
			<#list projects as project>
				<div class="module">
					<div class="module-title">
						${project.title}
					</div>
					<div class="module-description">
						${project.description}
					</div>
					<a href="${springMacroRequestContext.contextPath}/projects/${project.id}" class="module-button">
						Перейти к проекту
					</a>
				</div>
			</#list>
		</div>
	</div>
</body>
</html>