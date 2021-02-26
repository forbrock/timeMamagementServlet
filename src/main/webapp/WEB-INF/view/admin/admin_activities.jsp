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
          <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3">
            <h1 class="h2" th:text="#{admin.activities.title}">Activities</h1>
            
          </div>

          <div class="table-responsive">
            <table class="table table-striped table-sm mt-3">
                <thead>
                <tr>
                    <th th:text="#{admin.activities.table.head.category}">category</th>
                    <th th:text="#{admin.activities.table.head.name}">name</th>
                    <th th:text="#{admin.activities.table.head.actions}">actions</th>
                </tr>
                </thead>
                <tbody>
                <div th:each="activity : ${activities}">
                    <tr>
                        <td><a th:text="${activity.getName()}">name</a></td>
                        <td><a>category</a></td>
                        <td>
                          <a class="m-2" href="#" th:text="#{admin.activities.table.button.edit}" 
                            th:href="@{/admin/activity/edit/{id}(id=${activity.getId()})}">edit</a>
                            <a href="#" th:text="#{admin.activities.table.button.delete}" 
                              th:href="@{/admin/activity/delete/{id}(id=${activity.getId()})}">disable</a>
                          </td>
                    </tr>
                </div>
                </tbody>
            </table>
          </div>
          <div th:replace="fragments/modal_new_activity :: add_activity"></div>
        </main>
      </div>
    </div>
    <div th:replace="fragments/admin_page :: footer"></div>
 </body>
 </html>