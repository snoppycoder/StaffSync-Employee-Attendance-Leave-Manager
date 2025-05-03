const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

async function main() {
    //use this to erase the data first and populate again
    // await prisma.user.deleteMany();
    // console.log('Deleted all existing users.');

    const users = [
        { username : "alice", email: 'alice@example.com', password : 'password' },
        { username : "bob",  email : 'bob@example.com', password : 'password' },
        { username : "sam", email: 'charlie@example.com', password : 'password'  },
    ];

    // Insert users into the database
    for (const user of users) {
        await prisma.user.create({
            data: user,
        });
        console.log(`Created user: ${user.email}`);
    }
}

main()
    .catch((e) => {
        console.error(e);
        process.exit(1);
    })
    .finally(async () => {
        await prisma.$disconnect();
    });