<#import "parts/common.ftl" as c>
<@c.page "Главное меню">
    <#include "parts/navbar.ftl">
<h1 class="text-center">Изучите любой доступный язык совершенно бесплатно</h1>

<div class="container my-3 bg-light">
    <img src="/img/bgimg.png" width="1110px" height="500px" class="p-2">
</div>

<div class="container" style="align-items: center; justify-content: center; display: flex">
    <a href="/courses" class="btn btn-lg btn-success rounded my-1">Перейти к списку курсов</a>
</div>

<footer class="row justify-content-center bg-dark" style="position: absolute; bottom: 0; width: 100%;">
    <p class="text-light">&copy; 2020 BSUIR, Zherebilo AV</p>
</footer>

<style>
    body {
        background-color: silver;
    }
</style>
</@c.page>