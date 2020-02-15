<#import "parts/common.ftl" as c>
<@c.page "Авторизация">
<header class="row justify-content-center bg-info">
    <h1>Войдите в систему</h1>
</header>
<div class="container"><br>
    <div class="row justify-content-center">
        <form class="col-4 jumbotron" action="/login" name="authorize" method="post" style="position: absolute; top: 20%">
            <h2 class="text-center">Меню авторизации</h2>
            <label for="inputUsername" class="mb-2 mr-sm-2">Логин:</label>
            <input type="text" name="username" id="inputUsername" class="form-control" placeholder="Логин" required autofocus><br>
            <label for="password" class="mb-2 mr-sm-2">Пароль:</label>
            <input type="password" name="password" id="inputPassword" class="form-control" placeholder="Пароль" required><br>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-lg btn-primary btn-block" type="submit">Войти</button><br>
            Нет учётной записи?<a class="text-info btn" href="/registration">Зарегистрируйтесь</a>
        </form>
    </div>
</div>
<footer class="row justify-content-center bg-dark" style="position: absolute; bottom: 0; width: 100%;">
    <p class="text-light">&copy; 2020 BSUIR, Zherebilo AV</p>
</footer>
</@c.page>