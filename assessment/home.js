const adminUsername = "Admin";
const adminPassword = "admin@123";

let employees = [
  { name: "Employee1", email: "employee1@gmail.com", mobile: "1234567890" },
  { name: "Employee2", email: "employee2@gmail.com", mobile: "9876543210" }
];

function login() {
  var username = document.getElementById("username").value;
  var password = document.getElementById("password").value;

  if (username === adminUsername && password === adminPassword) {
    document.getElementById("login-page").style.display = "none";
    document.getElementById("table-page").style.display = "block";
    displayEmployeeTable();
  } else {
    window.alert("Invalid username or password.");
  }
}

function displayEmployeeTable() {
  const table = document.getElementById("employee-table");

  while (table.rows.length > 1) {
    table.deleteRow(1);
  }

  employees.forEach((employee) => {
    const newRow = table.insertRow(-1);

    const nameCell = newRow.insertCell(0);
    const emailCell = newRow.insertCell(1);
    const mobileCell = newRow.insertCell(2);
    const deleteCell = newRow.insertCell(3);

    nameCell.textContent = employee.name;
    emailCell.textContent = employee.email;
    mobileCell.textContent = employee.mobile;
    deleteCell.textContent = "Delete";
    deleteCell.className = "delete-button";
    deleteCell.onclick = function() {
      deleteEmployee(newRow);
    };

    nameCell.onclick = function() {
      showEmployeeDetail(employee);
    };
    emailCell.onclick = function() {
      showEmployeeDetail(employee);
    };
    mobileCell.onclick = function() {
      showEmployeeDetail(employee);
    };

    deleteCell.style.color="red";
  });
}

function showEmployeeDetail(employee) {
  document.getElementById("employee-name").textContent = "Name: " + employee.name;
  document.getElementById("employee-email").textContent = "Email: " + employee.email;
  document.getElementById("employee-mobile").textContent = "Mobile Number: " + employee.mobile;

  document.getElementById("table-page").style.display = "none";
  document.getElementById("detail-page").style.display = "block";
}

function goBack() {
  document.getElementById("detail-page").style.display = "none";
  document.getElementById("add-employee-page").style.display = "none";
  document.getElementById("table-page").style.display = "block";
}

function addEmployeeForm() {
  document.getElementById("table-page").style.display = "none";
  document.getElementById("add-employee-page").style.display = "block";
}

function addEmployee(event) {
  event.preventDefault();

  const name = document.getElementById("new-employee-name").value;
  const email = document.getElementById("new-employee-email").value;
  const mobile = document.getElementById("new-employee-mobile").value;

  if (!name || !email || !mobile) {
    alert("Please fill in all fields.");
    return;
  }

  const newEmployee = { name, email, mobile };

  employees.push(newEmployee);

  displayEmployeeTable();

  document.getElementById("new-employee-name").value = "";
  document.getElementById("new-employee-email").value = "";
  document.getElementById("new-employee-mobile").value = "";

  goBack();
}

function deleteEmployee(row) {
  const table = document.getElementById("employee-table");
  const rowIndex = row.rowIndex - 1;

  employees.splice(rowIndex, 1);

  table.deleteRow(row.rowIndex);
}