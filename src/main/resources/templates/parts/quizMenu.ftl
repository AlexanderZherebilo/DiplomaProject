<#include "security.ftl">

<nav class="navbar navbar-expand navbar-light bg-light">
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#quizMenu" aria-controls="quizMenu" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="quizMenu">
        <ul class="navbar-nav mr-auto flex-column">
            <div class="navbar-header">
                <a class="navbar-brand" href="">Опции</a>
            </div>
            <li class="nav-item">
                <a class="nav-link" href="/topics/${topic.id}/setQuiz">Настроить задание</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="">Редактировать статью</a>
            </li>
        </ul>
    </div>
</nav>