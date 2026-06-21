document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    
    form.addEventListener('submit', (event) => {
        const name = document.querySelector('#name').value;
        const email = document.querySelector('#email').value;

        if (name.trim() === '' || email.trim() === '') {
            alert('Please fill in all fields before submitting.');
            event.preventDefault(); // Stop the form from submitting
        } else {
            console.log('Registration submitted for:', name);
        }
    });
});
