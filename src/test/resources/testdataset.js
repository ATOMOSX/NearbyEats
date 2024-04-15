db = connect( 'mongodb://root:example@localhost:27017/proyecto_test?authSource=admin' );
db.users.insertMany([
    {
        _id: 'client1',
        profilePicture: 'yo.jpg',
        nickname: 'Atomos',
        city: 'Armenia',
        firstName: 'Juan David',
        lastName: 'López Muñoz',
        email: 'atomos29@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        isActive: true,
        role: 'CLIENT',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.User'
    },
    {
        _id: 'mod1',
        profilePicture: 'yo4.jpg',
        nickname: 'AtomosMod',
        city: 'Armenia',
        firstName: 'David Mod',
        lastName: 'Muñoz',
        email: 'atomosMod@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        isActive: true,
        role: 'MODERATOR',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.User'
    }
]);

db.places.insertMany([
    {
        _id: 'place1',
        name: 'Sazón criollo',
        description: 'Lorem ipsum',
        location: {
            type: 'point',
            coordinates: [
                4.560493469238281,
                -75.65943908691406
            ]
        },
        images: [
            'picture'
        ],
        schedule: [
            {
                dayOfWeek: 'MONDAY',
                openingTime: '10:00',
                closingTime: '20:30'
            },
            {
                dayOfWeek: 'TUESDAY',
                openingTime: '10:00',
                closingTime: '20:30'
            }
        ],
        phones: [
            '1234567',
            '230875'
        ],
        categories: [
            'RESTAURANT'
        ],
        status: 'WAITING',
        createdBy: 'client1',
        creationDate: '2024-04-04T22:51:08.832850',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Place'
    }
]);
db.comments.insertMany([
    {
        _id: "comment1",
        text: "Excelente sitio, muy buena atención",
        date: '2024-04-04T22:51:08.832850',
        user: 'client1',
        place: 'place1',
        rating: 5,
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Comment'
    }
]);