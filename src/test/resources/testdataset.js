db = connect( 'mongodb://root:example@localhost:27017/proyecto_test?authSource=admin' );
db.clientes.insertMany([
    {
        _id: 'client1',
        profilePhoto: 'yo.jpg',
        nickname: 'Atomos',
        city: 'Armenia',
        firstName: 'Juan David',
        lastName: 'López Muñoz',
        email: 'atomos29@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        status: 'ACTIVE',
        _class: 'co.edu.uniquindio.proyecto.model.documents.Client'
    },
    {
        _id: 'client2',
        profilePhoto: 'yo2.jpg',
        nickname: 'Atomos3',
        city: 'Armenia',
        firstName: 'Juan',
        lastName: 'López',
        email: 'atomos2@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        status: 'ACTIVE',
        _class: 'co.edu.uniquindio.proyecto.model.documents.Client'
    },
    {
        _id: 'client3',
        profilePhoto: 'yo3.jpg',
        nickname: 'Atomos2',
        city: 'Armenia',
        firstName: 'David',
        lastName: 'Muñoz',
        email: 'atmos29@correo.com',
        password: '$2a$10$Se1GLM8hfjywo69nPVtkhekiHzUbU6uAqqQhe8zk25RLZoLKzaGxW',
        status: 'ACTIVE',
        _class: 'co.edu.uniquindio.proyecto.model.documents.Client'
    }
]);

db.negocios.insertMany([
    {
        _id: 'place1',
        name: 'Sazón criollo',
        description: 'Lorem ipsum',
        location: {
            latitude: 4.560493469238281,
            length: -75.65943908691406
        },
        pictures: [
            'picture'
        ],
        schedule: [
            {
                dayOfWeek: 'Monday',
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
        creationDate: '2024-04-04T22:51:08.832850',
        _class: 'co.edu.uniquindio.proyecto.model.documents.Place'
    }
]);
db.comentarios.insertMany([
    {
        commentary: "Excelente sitio, muy buena atención",
        date: '2024-04-04T22:51:08.832850',
        clientId: 'client1',
        placeId: 'place1',
        score: 5,
        _class: 'co.edu.uniquindio.proyecto.modelo.documentos.Commentary'
    }
]);