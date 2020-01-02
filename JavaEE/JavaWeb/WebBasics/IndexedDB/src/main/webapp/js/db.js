// Inicializamos IndexedDB y sus APIs
window.indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
window.IDBTransaction = window.IDBTransaction || window.webkitIDBTransaction || window.msIDBTransaction;
window.IDBKeyRange = window.IDBKeyRange || window.webkitIDBKeyRange || window.msIDBKeyRange;

// Verificamos que el Browser lo soporte
if (!window.indexedDB) {
    window.alert("Your browser doesn't support a stable version of IndexedDB.");
}

// Creamos un espacio de nombres
var javacuriosities = {};

// Creamos unos atributos
javacuriosities.indexedDB = {};
javacuriosities.indexedDB.db = null;

javacuriosities.indexedDB.open = function() {
    // Creamos la DB o la abrimos en caso que exista, los parámetros son el nombre y la version
    var request = indexedDB.open("Javacuriosities", 1);

    // Este método es invocado cuando la DB se crea o cuando cambia su version
    request.onupgradeneeded = function(e) {
        javacuriosities.indexedDB.db = e.target.result;

        var db = javacuriosities.indexedDB.db;

        // Creamos el almacén y asignamos algunas propiedades
        db.createObjectStore("tasks", {keyPath: "id", autoIncrement: true});
    };

    request.onsuccess = function(e) {
        javacuriosities.indexedDB.db = e.target.result;

        javacuriosities.indexedDB.showAll();
    };

    request.onerror = javacuriosities.indexedDB.onerror;
};

// Definimos una función onerror
javacuriosities.indexedDB.onerror = function(e) {
    console.log(e);
};

// ************************************** //
// DB Functions
// ************************************** //
javacuriosities.indexedDB.showAll = function() {
    // Obtenemos la lista
    var items = document.getElementById("items");

    // Limpiamos su contenido
    items.innerHTML = "";

    var db = javacuriosities.indexedDB.db;

    // Indicamos el almacén que queremos abrir y en que modo
    var trans = db.transaction(["tasks"], "readwrite");
    var store = trans.objectStore("tasks");

    // Obtenemos los datos del almacén de datos
    var cursorRequest = store.openCursor();

    // Iteramos el cursor
    cursorRequest.onsuccess = function(e) {
        var result = e.target.result;

        // Cuando es NULL significa que terminamos
        if (!result) {
            return;
        }

        renderTask(result.value);
        result.continue();
    };

    cursorRequest.onerror = javacuriosities.indexedDB.onerror;
};

javacuriosities.indexedDB.addTask = function(pendingTask) {
    var db = javacuriosities.indexedDB.db;
    var trans = db.transaction(["tasks"], "readwrite");
    var store = trans.objectStore("tasks");

    var data = {"text": pendingTask, "timeStamp": new Date().getTime()};

    var request = store.put(data);

    request.onsuccess = function(e) {
        javacuriosities.indexedDB.showAll();
    };

    request.onerror = function(e) {
        console.log("Error Adding: ", e);
    };
};

javacuriosities.indexedDB.deleteTask = function(id) {
    var db = javacuriosities.indexedDB.db;
    var trans = db.transaction(["tasks"], "readwrite");
    var store = trans.objectStore("tasks");

    var request = store.delete(id);

    request.onsuccess = function(e) {
        javacuriosities.indexedDB.showAll();
    };

    request.onerror = function(e) {
        console.log("Error Adding: ", e);
    };
};

// ************************************** //
// Render Functions
// ************************************** //
function renderTask(row) {
    var items = document.getElementById("items");
    var li = document.createElement("li");
    var a = document.createElement("a");
    var t = document.createTextNode(row.text);

    a.addEventListener("click", function() {
        javacuriosities.indexedDB.deleteTask(row.id);
    }, false);

    a.textContent = " [Borrar]";
    li.appendChild(t);
    li.appendChild(a);
    items.appendChild(li);
}

function init() {
    javacuriosities.indexedDB.open();
}

function addTask() {
    var task = document.getElementById("task");
    javacuriosities.indexedDB.addTask(task.value);
    task.value = "";
}

// Asignamos un Listener para el evento "DOMContentLoaded"
window.addEventListener("DOMContentLoaded", init());