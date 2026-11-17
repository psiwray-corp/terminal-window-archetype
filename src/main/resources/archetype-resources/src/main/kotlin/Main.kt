package ${package}

import com.psiwray.organization.terminal_window.Colors
import com.psiwray.organization.terminal_window.TerminalWindowConfig
import com.psiwray.organization.terminal_window.TerminalWindowService
import com.psiwray.organization.terminal_window.font.BitmapFont
import com.psiwray.organization.terminal_window.input.InputListener
import com.psiwray.organization.terminal_window.input.Key
import com.psiwray.organization.terminal_window.input.KeyEvent

public fun main() {
    val font =
        Thread.currentThread().contextClassLoader.getResourceAsStream("fonts/ascii-8x16.twf")
            ?.use { BitmapFont.parse(it) }
            ?: error("Missing font resource fonts/ascii-8x16.twf")

    TerminalWindowService.initialize().use { service ->
        service.openWindow(TerminalWindowConfig(font = font)).use { window ->
            window.matrix.write(0, 0, "Hello, terminal-window!", Colors.WHITE, Colors.BLACK)
            window.matrix.write(0, 1, "Press Q to quit.", Colors.WHITE, Colors.BLACK)
            window.addInputListener(
                object : InputListener {
                    override fun onKey(event: KeyEvent) {
                        if (event.key == Key.Q) window.requestClose()
                    }
                },
            )
            while (!window.shouldClose) window.frame()
        }
    }
}
