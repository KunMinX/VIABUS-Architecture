package com.kunminx.viabus.business.bus;

import com.kunminx.architecture.business.bus.IRequest;
import com.kunminx.viabus.repertory.bean.NoteBean;

/**
 * @author KunMinX
 * Create at 2018/9/19
 */
public interface INoteRequest extends IRequest{

    void queryList();

    void queryEntity(long id);

    void insert(NoteBean bean);

    void update(NoteBean bean);

    void delete(NoteBean bean);
}
