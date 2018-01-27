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
    		game_error_1001: 'Invalid service parameters!',
    		game_error_1002: '{0} is an invalid board!',
    		game_error_1003: 'You are already playing on board {0}!',
    		game_error_1004: '{0} is an invalid user!',
    		game_error_1005: 'The game has not started yet!',
    		game_error_1006: 'It is not your turn!',
    		game_error_1007: 'Cell {0} {1} is not empty!',
    		game_error_1008: 'Board owner cannot leave the board!',
    		game_error_1009: 'Invalid parameters!',
    		game_error_1010: 'You are not on board {0}!',
    		game_error_1011: 'The game has started!',
    		game_error_1012: 'Words {0} are not defined in {1} language!',
    		game_error_1013: 'Rack is not valid!',
    		game_error_1014: 'Starting cell cannot be empty!',
    		game_error_1015: 'Words {0} are not linked with any existing words!'
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
            game_error_1001: 'Geçersiz servis parametreleri!',
            game_error_1002: '{0} geçerli bir oyun değil!',
            game_error_1003: '{0} oyununa zaten katıldın!',
            game_error_1004: '{0} geçerli bir kullanıcı değil!',
            game_error_1005: 'Oyun henüz başlamadı!',
            game_error_1006: 'Senin sıran değil!',
            game_error_1007: 'Hücre {0} {1} boş değil!',
            game_error_1008: 'Masa sahibi masayı terkedemez!',
            game_error_1009: 'Geçersiz servis parametreleri!',
            game_error_1010: '{0} oyununda değilsin!',
            game_error_1011: 'Oyun başladı!',
            game_error_1012: '{0} kelimeleri {1} dilinde bulunamadı!',
            game_error_1013: 'Tabla doğrulanamadı!',
            game_error_1014: 'Başlangıç hücresi boş bırakılamaz!',
            game_error_1015: '{0} kelimeleri mevcut kelimeler ile bağlantılı değil!'
        }
    );
    $translateProvider.preferredLanguage('tr');
});