$(function () {
    let getMessageElement = function (message) {
        let mItem = $('<div class="message-item"></div>');
        let header = $('<div class="message-header"></div>');

        header.append($('<div class="date-time">' + message.dateTime + '</div>'));
        header.append($('<div class="username">' + message.username + '</div>'));

        let textElement = $('<div class="message-text"></div>');
        textElement.text(message.text)

        mItem.append(header, textElement);
        return mItem;
    };

    let getUserElement = function (user) {
        let uItem = $('<div class="user-item"></div>');
        uItem.text(user.name);
        return uItem;
    };

    let updateMessage = function () {
        // $('#messages-list').html('<i>Сообщений нет</i>');
        $.get('/messages', {}, function (response) {
            if (response.length == 0) {
                return;
            }
            $('#messages-list').html('');


            for (let i in response) {
                let element = getMessageElement(response[i]);
                let ml = $("#messages-list");
                ml.append(element);
                ml.scrollTop(ml.prop('scrollHeight'));

            }
        });
    };

    let updateUsers = function () {
        // $('#users-list').html(' <i>Здесь никого нет</i>');
        $.get('/users', {}, function (response) {
            if (response.length == 0) {
                return;
            }
            $('#users-list').html('');


            for (let i in response) {
                let element = getUserElement(response[i]);
                $('#users-list').append(element);
            }
        });
    };

    let initApplication = function () {
        $(".messages-and-users").css({display: 'flex'});
        $(".controls").css({display: 'flex'});

        $('.send-message').on('click', function () {
            let message = $('.new-message').val();
            $.post('/messages', {message: message}, function (response) {
                if (response.result) {
                    $('.new-message').val('');
                } else alert("Ошибка: повторите попытку позже");
            });
        });
        setInterval(updateMessage, 1000);
        setInterval(updateUsers, 1000);
    };

    let registerUser = function (name) {
        $.post("/auth", {name: name}, function (response) {
            if (response.result) {
                initApplication();
            }
        });
    };

    $.get('/init', {}, function (response) {
        if (!response.result) {
            let name = prompt("Введите ваше имя:");
            registerUser(name);
            return;
        }
        initApplication();
    });

    $(".new-message").bind('keypress', function(e)
    {
        if(e.which == 13)
        {
            $('.send-message').click();
        }
    });
});