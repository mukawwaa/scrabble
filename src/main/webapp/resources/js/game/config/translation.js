angular.module('scrabble').config(function($translateProvider) {
	'use strict';
    $translateProvider
        .translations('en', {
            menu_newGame: 'New Game',
            menu_activeGames: 'Active Games',
            menu_myGames: 'My Games',
            //
            boards_join: 'Join Board',
            boards_leave: 'Leave Board',
            boards_watch: 'Watch Board',
            boards_empty: 'There is not any active board.',
            boards_owner: 'Board Owner',
            boards_maxUserCount: 'Maximum User Count',
            boards_currentUserCount: 'Current User Count',
            boards_duration: 'Move Duration',
            boards_status: 'Status',
            boards_board: 'Board',
            boards_started: 'Started',
            boards_waitingUsers: 'Waiting users to join',
            boards_expited: 'Expired',
            boards_terminated: 'Terminated',
            //
            create_name: 'Name',
            create_userCount: 'User Count',
            create_duration: 'Move Duration',
            create_create: 'Create Board',
            create_validation_name: 'Name is required.',
            create_validation_userCount: 'User Count is required.',
            create_validation_duration: 'Duration is required.',
            //
            showBoard_play: 'Play',
            showBoard_points: 'Points',
            showBoard_chat_title: 'Direct Chat',
            showBoard_chat_send: 'Send',
            showBoard_chat_placeHolder: 'Type message ...',
            //
            generic_error_1001: 'Invalid service parameters!',
            //
            board_error_2001: '{0} is an invalid board!',
            board_error_2002: 'You are already playing on board {0}!',
            board_error_2003: 'Board owner cannot leave the board!',
            board_error_2004: 'You are not on board {0}!',
            board_error_2005: 'The game has not started yet!',
            board_error_2006: 'The game has started!',
            //
            game_error_3001: 'It is not your turn!',
            game_error_3002: 'Rack is not valid!',
            game_error_3003: 'Starting cell cannot be empty!',
            game_error_3004: 'Cell {0} {1} is not empty!',
            game_error_3005: 'Words {0} are not linked with any existing words!',
            game_error_3006: 'Words {0} are not defined in {1} language!',
            //
            user_error_4001: 'User e-mail is not valid!',
            user_error_4002: 'E-mail already in use!',
            user_error_4003: 'Username already in use!',
            user_error_4004: 'Name or surname length is not enough!',
            user_error_4005: 'Name or surname must contains valid characters!',
            user_error_4006: 'Password must contain 1 capital letter, 1 short letter, 1 number and 1 special character!',
            user_error_4007: '{0} is an invalid user!'
        })
        .translations('tr', {
            menu_newGame: 'Yeni Oyun',
            menu_activeGames: 'Aktif Oyunlar',
            menu_myGames: 'Benim Oyunlarım',
            //
            boards_join: 'Masaya Katıl',
            boards_leave: 'Masadan Ayrıl',
            boards_watch: 'Masayı İzle',
            boards_empty: 'Aktif masa bulunmamaktadır.',
            boards_owner: 'Masa Sahibi',
            boards_maxUserCount: 'Maksimum Oyuncu Sayısı',
            boards_currentUserCount: 'Masadaki Oyuncu Sayısı',
            boards_duration: 'Hamle Süresi',
            boards_status: 'Masa Durumu',
            boards_board: 'Masa',
            boards_started: 'Başladı',
            boards_waitingUsers: 'Oyuncular bekleniyor',
            boards_expited: 'Sona erdi',
            boards_terminated: 'İptal edildi',
            //
            create_name: 'Ad',
            create_userCount: 'Oyuncu Sayısı',
            create_duration: 'Hamle Süresi',
            create_create: 'Masa Oluştur',
            create_validation_name: 'Ad zorunludur.',
            create_validation_userCount: 'Oyuncu Sayısı zorunludur.',
            create_validation_duration: 'Hamle Süresi zorunludur.',
            //
            showBoard_play: 'Oyna',
            showBoard_points: 'Puan',
            showBoard_chat_title: 'Mesajlaşma',
            showBoard_chat_send: 'Gönder',
            showBoard_chat_placeHolder: 'Mesajınızı yazın ...',
            //
            generic_error_1001: 'Geçersiz servis parametreleri!',
            //
            board_error_2001: '{0} geçerli bir oyun değil!',
            board_error_2002: '{0} oyununa zaten katıldın!',
            board_error_2003: 'Masa sahibi masayı terkedemez!',
            board_error_2004: '{0} oyununda değilsin!',
            board_error_2005: 'Oyun henüz başlamadı!',
            board_error_2006: 'Oyun başladı!',
            //
            game_error_3001: 'Senin sıran değil!',
            game_error_3002: 'Tabla doğrulanamadı!',
            game_error_3003: 'Başlangıç hücresi boş bırakılamaz!',
            game_error_3004: 'Hücre {0} {1} boş değil!',
            game_error_3005: '{0} kelimeleri mevcut kelimeler ile bağlantılı değil!',
            game_error_3006: '{0} kelimeleri {1} dilinde bulunamadı!',
            //
            user_error_4001: 'Geçersiz e-mail adresi!',
            user_error_4002: 'E-mail adresi başka bir kullanıcı tarafından kullanılıyor!',
            user_error_4003: 'Kullanıcı adı başka bir kullanıcı tarafından kullanılıyor!',
            user_error_4004: 'Ad ve soyad 1 karakterden fazla uzunlukta olmalıdır!',
            user_error_4005: 'Ad ve soyad sadece alfabetik karakterlerden oluşmalıdır!',
            user_error_4006: 'Şifrede en az 1 büyük harf, 1 küçük harf, 1 sayı ve bir özel karakter kullanılmalıdır!',
            user_error_4007: '{0} geçerli bir kullanıcı değil!'
        }
    );
    $translateProvider.preferredLanguage('en');
});