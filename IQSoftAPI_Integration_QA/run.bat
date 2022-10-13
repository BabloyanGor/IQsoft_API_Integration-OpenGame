
CALL mvn clean install
allure serve allure-results
allure generate allure-results --clean -o allure-report
pause
cmd /k

