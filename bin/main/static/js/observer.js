class Observer {
  constructor() {
    this.handlers = {};
  }

  register(eventName, handler, context) { 
    let handlers = this.handlers[eventName]; 

    if (handlers === undefined) { 
      handlers = this.handlers[eventName] = new Array();
    }

    handlers.push({
      handler : handler,
      context : context  
    });
  }

  unregister(eventName, handler, context) {
    let handlers = this.handler[eventName];
    if (handlers === undefined) { 
      return ;
    }

    for (let i = 0; i < handlers.length; i++) {
      let currentHandler = handlers[i];
      
      if (handler === currentHandler['handler'] && context === currentHandler['context']) {
        handlers.splice(i, 1);
        break;
      }
    }
  }

  notify(eventName, data) { 
    let handlers = this.handlers[eventName];
    if (handlers === undefined) {
      return;
    }

    for (let i = 0; i < handlers.length; i++) {
      let currentHandler = handlers[i];
      currentHandler['handler'].call(currentHandler['context'], data);
    }
  }
}