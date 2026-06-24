// 1. Move these functions OUTSIDE the event listener
function showDetails(id) {
    fetch('/api/students/' + id)
        .then(response => response.json())
        .then(data => {
            const detailsContent = document.getElementById('detailsContent');
            detailsContent.innerHTML = `
                <p><strong>Name:</strong> ${data.name}</p>
                <p><strong>Age:</strong> ${data.age}</p>
                <p><strong>Email:</strong> ${data.email}</p>
                <p><strong>Phone:</strong> ${data.phone}</p>
                <p><strong>Course:</strong> ${data.course}</p>
            `;
            document.getElementById('detailsModal').style.display = 'block';
        })
        .catch(error => console.error('Error fetching student details:', error));
}

function applyFilter() {
    const checkboxes = document.querySelectorAll('input[type="checkbox"]:checked');
    let courses = Array.from(checkboxes).map(cb => cb.value);
    const url = '/admin/students/filter?courses=' + encodeURIComponent(courses.join(','));

    fetch(url)
        .then(response => response.json())
        .then(students => {
            const tbody = document.getElementById('tableBody');
            tbody.innerHTML = ''; 

            students.forEach(student => {
                const row = `<tr onclick="showDetails(${student.id})">
                                <td>${student.name}</td>
                                <td>${student.course}</td>
                             </tr>`;
                tbody.innerHTML += row;
            });
            document.getElementById('filterModal').style.display = 'none';
        });
}

// 2. Keep the DOMContentLoaded block for form validation only
document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    if (form) { // Add a check to ensure form exists before adding listener
        form.addEventListener('submit', (event) => {
            const name = document.querySelector('#name').value;
            const email = document.querySelector('#email').value;

            if (name.trim() === '' || email.trim() === '') {
                alert('Please fill in all fields before submitting.');
                event.preventDefault();
            } else {
                console.log('Registration submitted for:', name);
            }
        });
    }
});
