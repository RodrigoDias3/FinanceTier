# **Finance Tier**
Finance Tier is a financial management application designed to help users track their income, expenses, and savings efficiently. With this tool, you can create an account, log transactions, set up savings with interest rates, and even simulate salaries and taxes based on Portugal's IRS tables.

## **Features**
- **User Account Management** – Securely create and manage your account.
- **Expense & Income Tracking** – Log transactions to monitor your finances.
- **Savings Management** – Create savings accounts with customizable interest rates.
- **Salary & Tax Simulation** – Calculate net salary and taxes using Portugal's IRS tables.

---

## **Installation**
- Clone the repository:
```
git clone https://github.com/your-username/finance-tier.git
```

- Now we need to creat a MySQL Data Base. Make sure you have MySQL installed before making this query.
```
create database db_name;

create user 'user_test'@'localhost' identified by 'passworduserteste';

grant all privileges on db_name.* to 'user_test'@'localhost';
```
- Now you need to add your MySQL configurations in to `application.properties` on `\src\main\resources\`
- Run `DemoApplication` Class
- Just go to your Web Browser and access `http://localhost:8091/`

---

## **Usage**
- After creating an account, you can start adding transactions.
- Manage your savings by setting up goals and interest rates.
- Use the salary simulator to estimate your take-home pay.

## **Examples**
- Home Page (after login)

  ![image](https://github.com/user-attachments/assets/5b353336-3263-4100-8667-5a542d5e2760)

- **Simulation Page**

  Once you've added your expected salary, a donu-type graph will appear next to it with tax percentages and below it a description of all the values you could use, such as:
  - Gross Salary
  - Food allowance
  - Tax rate
  - Amount taxed
  - Social security at 11%
  - Net salary

  ![image](https://github.com/user-attachments/assets/77c6fad4-e06f-4962-8460-7b97ee891dce)

- **Transaction Table**
  
  The transactions are organized in a table from the most recent to the oldest, noting that each transaction has a date, a category, an amount and a type. \
  All expenses appear in red and all income appears in green.

  ![image](https://github.com/user-attachments/assets/8766b8d8-f738-4f07-89dc-65d23778a486)

- **New Transaction Modal**

  When we click on `Add Transaction`, this Modal appears. Just fill in each piece of information and click on `Save Transaction`.

  ![image](https://github.com/user-attachments/assets/b5c55aa9-4ca0-4e23-8da5-b5940819589e)

- **Savings Page**

  On this page, as with transactions, you can also create new savings. When you click on `Receive Interest`, the interest rate is added to your account.

  ![image](https://github.com/user-attachments/assets/71ab9377-64d1-4353-9439-1a3dec27ded0)
