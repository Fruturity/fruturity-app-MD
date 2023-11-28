const express = require('express');
const router = express.Router();

router.get('/banana', (req,res) => {
    res.send('Get banana history');
})

router.get('/mango', (req,res) => {
    res.send('Get mango history');
})

router.get('/banana/:id', (req, res) => {
    res.send(`Ini pisang ke ${req.params.id}`);
})

router.get('/mango/:id', (req,res)=>{
    res.send(`Ini mangga ke ${req.params.id}`);
})

module.exports = router;
