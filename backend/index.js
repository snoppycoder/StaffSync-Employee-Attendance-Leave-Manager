const { PORT } = require("./utils/config");
const { info, error } = require("./utils/logger");
const app = require('./app')


app.listen(PORT || 3000, () => {
    console.log(`Server running on port ${PORT}`);
});
  