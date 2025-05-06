$(document).ready(function() {
    $('.save-status-btn').click(function() {
        const taskId = $(this).data('task-id');
        const projectId = $('.status-select').data('project-id');
        const teamId = window.location.pathname.split('/')[2];
        const statusId = $('.status-select').val();
        const csrfToken = $('#csrfToken').val();

        $.ajax({
            url: `/team/${teamId}/project/${projectId}/update`,
            type: 'POST',
            headers: {
                'X-CSRF-TOKEN': csrfToken
            },
            data: {
                taskId: taskId,
                statusId: statusId
            },
            success: function(response) {
                location.reload();
            },
            error: function(xhr) {
                alert('Ошибка: ' + xhr.responseText);
            }
        });
    });
});