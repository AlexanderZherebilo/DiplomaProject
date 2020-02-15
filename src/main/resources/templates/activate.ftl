<#import "parts/common.ftl" as c>
<@c.page "Активация аккаунта">
<header class="row justify-content-center bg-info">
    <h1>Регистрация пользователя Solo Learn</h1>
</header>
<div class="container"><br>
    <div class="row justify-content-center">
        <div class="jumbotron">
            <h2 class="text-center">Подтверждение регистрации:</h2><br>
            <span class="text-center text-${color}">${message}</span><br><br>
            <span class="text-gray text-center">Эта вкладка закроется через несколько секунд</span>
        </div>
    </div>
</div>
<footer class="row justify-content-center bg-dark" style="position: absolute; bottom: 0; width: 100%;">
    <p class="text-light">&copy; 2020 BSUIR, Zherebilo AV</p>
</footer>
</@c.page>

<script>
    setTimeout(function () {
        this.close();
    }, 5000);
</script>