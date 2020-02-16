<#import "parts/common.ftl" as c>

<@c.page "Добавление языка программирования">
    <#include "parts/navbar.ftl">
    <div class="container">
        <h2>Добавление курса языка программирования</h2>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Название языка:</label>
                <div class="col-sm-3">
                    <input type="text" name="name" class="form-control" required/>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Описание:</label>
                <div class="col-sm-3">
                    <textarea name="description" rows="6" class="form-control"></textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Изображение:</label>
                <div class="col-sm-3">
                    <input type="file" name="image" id="customFile">
                    <label class="custom-file-label" for="customFile">Размер до 1 Мб</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button class="btn btn-primary" type="submit">Добавить</button>
        </form>
    </div>

</@c.page>