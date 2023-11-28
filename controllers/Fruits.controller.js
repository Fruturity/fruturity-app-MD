const fruits = require('../models/Fruits.model');

const getFruits = ((req, res) => {
    res.json(fruits);
});
