db = connect( 'mongodb://root:password@localhost:27017/nearby_eats?authSource=admin' );
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
        _id: 'client2',
        profilePicture: 'yo2.jpg',
        nickname: 'Atomos2',
        city: 'Armenia',
        firstName: 'Juan',
        lastName: 'López',
        email: 'atoms29@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        isActive: true,
        role: 'CLIENT',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.User'
    },
    {
        _id: 'client3',
        profilePicture: 'yo3.jpg',
        nickname: 'Atomos3',
        city: 'Armenia',
        firstName: 'David',
        lastName: 'Muñoz',
        email: 'atomo3s29@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        isActive: true,
        role: 'CLIENT',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.User'
    },
    {
        _id: 'client4',
        profilePicture: 'yo4.jpg',
        nickname: 'Atomos4',
        city: 'Armenia',
        firstName: 'Juan 4',
        lastName: 'López 4',
        email: 'atomos294@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        isActive: true,
        role: 'CLIENT',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.User'
    },
    {
        _id: 'client5',
        profilePicture: 'yo5.jpg',
        nickname: 'Atomos5',
        city: 'Armenia',
        firstName: 'Juan 5',
        lastName: 'López 5',
        email: 'atomos259@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        isActive: true,
        role: 'CLIENT',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.User'
    },
    {
        _id: 'mod1',
        profilePicture: 'yo6.jpg',
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
    },
    {
        _id: 'place2',
        name: 'Éxito',
        description: 'Lorem ipsum éxito',
        location: {
            type: 'point',
            coordinates: [
                15.560493469238281,
                -85.65943908691406
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
        createdBy: 'client2',
        creationDate: '2024-04-04T22:51:08.832850',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Place'
    },
    {
        _id: 'place3',
        name: 'Mocawa',
        description: 'Lorem ipsum Mocawa',
        location: {
            type: 'point',
            coordinates: [
                11.560493469238281,
                -95.65943908691406
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
        createdBy: 'client3',
        creationDate: '2024-04-04T22:51:08.832850',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Place'
    },
    {
        _id: 'place4',
        name: 'Olímpica',
        description: 'Lorem ipsum olímpica',
        location: {
            type: 'point',
            coordinates: [
                1.560493469238281,
                -83.65943908691406
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
        createdBy: 'client4',
        creationDate: '2024-04-04T22:51:08.832850',
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Place'
    },
    {
        _id: 'place5',
        name: 'Casita',
        description: 'Lorem ipsum casita',
        location: {
            type: 'point',
            coordinates: [
                13.560493469238281,
                -55.65943908691406
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
        createdBy: 'client5',
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
        place: 'place2',
        rating: 5,
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Comment'
    },
    {
        _id: "comment2",
        text: "Excelente sitio, muy buena atención",
        date: '2024-04-04T22:51:08.832850',
        user: 'client2',
        place: 'place1',
        rating: 5,
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Comment'
    },
    {
        _id: "comment3",
        text: "Excelente sitio, muy buena atención",
        date: '2024-04-04T22:51:08.832850',
        user: 'client3',
        place: 'place2',
        rating: 5,
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Comment'
    },
    {
        _id: "comment4",
        text: "Excelente sitio, muy buena atención",
        date: '2024-04-04T22:51:08.832850',
        user: 'client4',
        place: 'place1',
        rating: 5,
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Comment'
    },
    {
        _id: "comment5",
        text: "Excelente sitio, muy buena atención",
        date: '2024-04-04T22:51:08.832850',
        user: 'client5',
        place: 'place2',
        rating: 5,
        _class: 'co.edu.uniquindio.nearby_eats.model.docs.Comment'
    },
]);