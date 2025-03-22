
window.onload = function () {
    updateTable();

    function updateTable() {
        const table = document.querySelector("#manga-table");
        const tablebody = document.querySelector("#manga-table tbody");
        tablebody.innerHTML = "";

        fetch("/getMangas", {
            method: "GET",
            headers: { "Content-Type": "application/json" },
        }).then(res => res.json())
            .then(data => {
                data.forEach(entry => {
                    var row = table.insertRow(-1);

                    var mangaID_cell = row.insertCell(0);
                    var title_cell = row.insertCell(1);
                    var language_cell = row.insertCell(2);
                    var hVolume_cell = row.insertCell(3);
                    var hChapter_cell = row.insertCell(4);
                    var removeButton_cell = row.insertCell(5);
                    mangaID_cell.textContent = entry["mangaID"];
                    title_cell.textContent = entry["title"];
                    language_cell.textContent = entry["lang"];
                    hVolume_cell.textContent = entry["h_Volume"];
                    hChapter_cell.textContent = entry["h_Chapter"];


                    //creates the delete Button
                    const button = document.createElement('button');
                    button.innerText = "Delete";
                    button.classList.add("Delete-btn");
                    button.addEventListener("click", (event => {
                        location.href = "/delete?id=" + entry["mangaID"];
                    }));
                    removeButton_cell.appendChild(button);
                })
            });
    }
}