

updateTable();

function updateTable() {
    const table = document.querySelector("#manga-table");
    const tablebody = document.querySelector("#manga-table tbody");
    tablebody.innerHTML = "";

    fetch("/api/getMangas", {
        method: "GET",
        headers: { "Content-Type": "application/json" },
    }).then(res => res.json())
        .then(data => console.log(data));
}