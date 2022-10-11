cd D:\IQsoft\IQsoftAPITesting\APIIQsoft\IQSoft_API_Test\IQsoftAPI
CALL mvn clean install
allure generate allure-results --clean -o allure-report
allure serve allure-results
pause
cmd /k