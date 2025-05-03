document.addEventListener('DOMContentLoaded', function() {
    const modal = document.getElementById('membersModal');
    window.openModal = function() {
        modal.style.display = 'block';
    };
    window.closeModal = function() {
        modal.style.display = 'none';
    };
    window.onclick = function(event) {
        if (event.target === modal) {
            closeModal();
        }
    };
});