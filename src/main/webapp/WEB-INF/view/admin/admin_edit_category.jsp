<html lang="en" xmlns:th="http://www.thymeleaf.org"
                xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">
	<head>
		<div th:replace="fragments/admin_page :: head"></div>
	</head>

  <body>
  
    <div th:replace="fragments/admin_page :: header"></div>
  
	<div class="container-fluid">
      <div class="row">
		<div th:replace="fragments/admin_page :: dashboard"></div>
			
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4 mt-5">
            <div
              class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">
              <h1 class="h2" th:text="#{admin.edit.category.title}">Edit category</h1>
            </div>
            <form action="#" method="post" id="edit-category" th:action="@{/admin/category/edit/{id}(id=${category.getId()})}" 
              th:object="${category}">
              <div class="form-group row">
                <label for="name" class="col-sm-2 col-form-label" th:text="#{admin.edit.category.form.label.name}">Name</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" id="name" placeholder="" 
                    th:placeholder="#{admin.edit.category.form.placeholder.name}" th:field="*{name}">
                  <div th:if="${#fields.hasErrors('name')}" th:errors="*{name}" class="alert alert-danger p-2"
                    role="alert">Validation error</div>
                </div>
              </div>
              <button type="submit" class="btn btn-sm btn-outline-secondary m-2" 
              th:text="#{admin.edit.category.form.button.save}">Save</button>
            </form>
          </main>    
      </div>
    </div>

	<div th:replace="fragments/admin_page :: footer"></div>
 </body>
 </html>