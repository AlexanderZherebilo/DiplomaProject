<#import "parts/common.ftl" as c>

<@c.page "Добавление раздела">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h2>Добавление нового раздела</h2>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Название главы:</label>
                <div class="col-sm-3">
                    <input type="text" name="name" class="form-control" required/>
                </div>
            </div>
            <div class="row justify-content-center my-2">
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить теги <b></b> в конец текста" onclick="bold()"><b>B</b></button>
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить теги <i></i> в конец текста" onclick="italic()"><i>I</i></button>
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить теги <s></s> в конец текста" onclick="strikethrough()"><s>S</s></button>
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить тег <tab> в конец текста" onclick="redstr()">красная строка</button>
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить теги <span align='center'></span> в конец текста" onclick="centerstr()">по центру</button>
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить теги <span align='right'></span> в конец текста" onclick="rightstr()">по правому краю</button>
                <button type="button" class="btn btn-outline-secondary mx-1" title="Добавить <span align='center'>***</span> в конец текста" onclick="centerstar()">***</button>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Теория:</label>
                <div class="col">
                    <textarea name="theory" id="theory" cols="4" rows="16" class="form-control"></textarea>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Добавить</button>
        </form>
    </div>

    <script>
        function bold() {
            document.getElementById('theory').value += "<b></b>";
        }
        function italic() {
            document.getElementById('theory').value += "<i></i>";
        }
        function strikethrough() {
            document.getElementById('theory').value += "<s></s>";
        }
        function redstr() {
            document.getElementById('theory').value += "<tab>";
        }
        function centerstr() {
            document.getElementById('theory').value += "<span align='center'></span>";
        }
        function rightstr() {
            document.getElementById('theory').value += "<span align='right'></span>";
        }
        function centerstar() {
            document.getElementById('theory').value += "<span align='center'>***</span>";
        }
    </script>

</@c.page>