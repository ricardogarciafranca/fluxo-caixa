db = db.getSiblingDB('fluxo_caixa');

db.createCollection("users");

var userDocument = {
    id: "51da8d58-1643-4335-9485-4d8daf32c941",
    username: "admin",
    password: "$2a$10$HC9xcDCzOvr5W5OGTFyD3Occ9Fbmi9Hz7mZknyP0.Vr74YFGVeeKO",
    authorities: [
        {
            authority: "admin",
            id: "1"
        }
    ]
};

db.users.insertOne(userDocument);


db.createCollection("lancamentos");


var lancamento1 = {
    id: "08e4469e-2450-4a9a-aed3-82a6877a2847",
    descricao: "Venda item 1",
    tipo: "CREDITO",
    valor: 10.1,
    consolidado: 10.1,
    dateTime: "2023-05-26T00:01:00",
    idUsuario: "51da8d58-1643-4335-9485-4d8daf32c941"
};

var lancamento2 = {
    id: "8ce07bcc-5498-4031-b6f9-a95fa6c6e611",
    descricao: "Venda item 2",
    tipo: "CREDITO",
    valor: 10.2,
    consolidado: 20.3,
    dateTime: "2023-05-26T00:02:00",
    idUsuario: "51da8d58-1643-4335-9485-4d8daf32c941"
};

var lancamento3 = {
    id: "4239e025-d619-489e-bf13-55138edfd56f",
    descricao: "Venda item 3",
    tipo: "CREDITO",
    valor: 10.2,
    consolidado: 30.5,
    dateTime: "2023-05-26T00:03:00",
    idUsuario: "51da8d58-1643-4335-9485-4d8daf32c941"
};

var lancamento4 = {
    id: "e1cf0541-3131-40b2-8292-c3a5efd4bbea",
    descricao: "Compra item 1",
    tipo: "DEBITO",
    valor: 5.5,
    consolidado: 25.0,
    dateTime: "2023-05-26T00:04:00",
    idUsuario: "51da8d58-1643-4335-9485-4d8daf32c941"
};

var lancamento5 = {
    id: "f57610d5-bba5-47cd-b048-852e73cb2939",
    descricao: "Compra item 2",
    tipo: "DEBITO",
    valor: 2.1,
    consolidado: 23.9,
    dateTime: "2023-05-26T00:05:00",
    idUsuario: "51da8d58-1643-4335-9485-4d8daf32c941"
};

db.lancamentos.insertOne(lancamento1);
db.lancamentos.insertOne(lancamento2);
db.lancamentos.insertOne(lancamento3);
db.lancamentos.insertOne(lancamento4);
db.lancamentos.insertOne(lancamento5);