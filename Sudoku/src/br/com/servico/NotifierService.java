package br.com.servico;

import br.com.ui.custom.botao.BotaoCheckStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class NotifierService {

    private final Map<EventoEnum, List<EventoListener>> listenerss = new HashMap<>(){{
      put(EventoEnum.LIMPAR_ESPACO, new ArrayList<>());
    }};

    public void subscribe(final EventoEnum tipoEvento, EventoListener listener){
        var selectedListeners = listenerss.get(tipoEvento);
        selectedListeners.add(listener);
    }


    public void notify(final EventoEnum eventType){
        listenerss.get(eventType).forEach(l -> l.update(eventType));
    }
}
