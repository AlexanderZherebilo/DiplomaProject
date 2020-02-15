<#import "parts/common.ftl" as c>
<@c.page "${language.name}">
    <#include "parts/navbar.ftl">
<div class="container">
    <div class="jumbotron">
        ${language.description}
    </div>
</div>
<footer class="row justify-content-center bg-dark" style="position: absolute; bottom: 0; width: 100%;">
    <p class="text-light">&copy; 2019 BSUIR, Zherebilo AV</p>
</footer>

</@c.page>