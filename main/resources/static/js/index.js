
window.onload = function () {
    updateTableForAuthor();
    updateTableforManga();


    function updateTableforManga() {
        const table = document.querySelector("#manga-table");
        const tablebody = document.querySelector("#manga-table tbody");
        tablebody.innerHTML = "";

        fetch("/api/mangas", {
            method: "GET",

        }).then(res => res.json())
            .then(data => {
                console.log(data);
         data.forEach(entry => {
                            var row = table.insertRow(-1);

                            var mangaID_cell = row.insertCell(0);
                            var title_cell = row.insertCell(1);
                            var language_cell = row.insertCell(2);
                            var hVolume_cell = row.insertCell(3);
                            var hChapter_cell = row.insertCell(4);
                            var state_cell = row.insertCell(5);
                            var mode_cell = row.insertCell(6);
                            var outDir_cell = row.insertCell(7);
                            var removeButton_cell = row.insertCell(-1);
                            mangaID_cell.textContent = entry["mangaID"];
                            title_cell.textContent = entry["title"];
                            language_cell.textContent = entry["language"];
                            hVolume_cell.textContent = entry["highestVolume"];
                            hChapter_cell.textContent = entry["highestChapter"];
                            mode_cell.textContent = entry["mode"];
                            outDir_cell.textContent = entry["outDir"];
                            //creates the delete Button
                            const button = document.createElement('button');
                            button.innerText = "Delete";
                            button.classList.add("Delete-btn");
                            button.addEventListener("click", (event => {
                                location.href = "/api/delete?mangaID=" + entry["mangaID"];
                            }));
                            removeButton_cell.appendChild(button);
                        })
            });
    }
     function updateTableForAuthor() {
            const table = document.querySelector("#author-table");
            const tablebody = document.querySelector("#author-table tbody");
            tablebody.innerHTML = "";

            fetch("/api/authors", {
                method: "GET",

            }).then(res => res.json())
                .then(data => {
                    console.log(data);
             data.forEach(entry => {
                                var row = table.insertRow(-1);

                                var name_cell = row.insertCell(0);
                                var numberOfMangas_cell = row.insertCell(1);
                                var language_cell = row.insertCell(2);
                                var mode_cell = row.insertCell(3);
                                var outDir_cell = row.insertCell(4);
                                var removeButton_cell = row.insertCell(-1);
                                name_cell.textContent = entry["name"];

                                numberOfMangas_cell.textContent = entry["numberOfMangas"];
                                mode_cell.textContent = entry["mode"];
                                outDir_cell.textContent = entry["outDir"];
                                language_cell.textContent = entry["language"];
                                //creates the delete Button
                                const button = document.createElement('button');
                                button.innerText = "Delete";
                                button.classList.add("Delete-btn");
                                button.addEventListener("click", (event => {
                                    location.href = "/api/delete?author=" + entry["name"];
                                }));
                                removeButton_cell.appendChild(button);
                            })
                });
        }
}