/**
 * Usuarios.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    idUsuario:{
      type: "number"
    },

    username:{
      type: "string"
    },

    password:{
      type: "string",
    },

    reservas:{
      collection:"Reservas",
      via:"idUsuario"
    }

  },

};

