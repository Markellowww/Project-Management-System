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
			Привет, ${user.firstname}!
		</div>
		<div class="choose-module">
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
				<a href="${springMacroRequestContext.contextPath}/team/new" class="module-add-btn">
					+
				</a>
			</div>
			<#list teams as team>
				<div class="module">
					<div class="module-title">
						${team.name}
					</div>
					<div class="module-description">
						${team.description}
					</div>
					<a href="${springMacroRequestContext.contextPath}/team/${team.id}" class="module-button">
						Перейти к команде
					</a>
				</div>
			</#list>
		</div>
	</div>
</body>
</html>